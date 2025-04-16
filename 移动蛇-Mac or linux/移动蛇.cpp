#include <iostream>
#include <unistd.h>
#include <termios.h>
#include <fcntl.h>
using namespace std;
const int W = 60;
const int H = 20;
void initTermios()
{
    struct termios newt;
    tcgetattr(0, &newt);
    newt.c_lflag &= ~(ICANON | ECHO); // 关闭缓冲和回显
    newt.c_cc[VMIN] = 0;              // 非阻塞模式
    newt.c_cc[VTIME] = 0;
    tcsetattr(0, TCSANOW, &newt);
}
// 格子类型
enum Gezitype
{
    EMPTY = 0,
    FOOD = 1,
    SNAKE = 2,
};
// 方向
enum Fx
{
    left = 1,
    top = 2,
    right = 3,
    down = 4,
};
// 地图类型；
class Map
{
public:
    Gezitype data[H][W];
    bool hasFood;
};
// 坐标类型
class Pos
{
public:
    int x;
    int y;
};
// 蛇的类型
class Snake
{
public:
    int snakel;
    Pos snake[H * W];
    Fx fx;
    bool CheckCollision()
    {
        // 只检测墙壁碰撞，不检测身体碰撞
        if (snake[0].x < 0 || snake[0].x >= W ||
            snake[0].y < 0 || snake[0].y >= H)
        {
            return true; // 撞墙
        }
        return false; // 允许穿过身体
    }
};
// 初始化蛇
void Snakeinit(Snake *s)
{
    s->snakel = 3;
    s->snake[0] = {10, 5};
    s->snake[1] = {9, 5};
    s->snake[2] = {8, 5};
    s->fx = Fx::left;
}
// 初始化地图
void Mapinit(Map *map)
{
    for (int i = 0; i < H; i++)
    {
        for (int j = 0; j < W; j++)
        {
            map->data[i][j] = Gezitype::EMPTY;
        }
    }
    map->hasFood = false;
}
/*
// 光标移动 + 显示字符
void drawChar(int x, int y, char ch)
{
    printf("\033[%d;%dH%c", y + 1, x + 1, ch);
}
    */
// 设置蛇蛇
void Setsnake(Snake *s, Map *map)
{
    int l = s->snakel;
    for (int i = 0; i < l; i++)
    {
        int x = s->snake[i].x;
        int y = s->snake[i].y;
        map->data[y][x] = Gezitype::SNAKE;
    }
}
// 移动蛇
void Movesnake(Snake *s)
{
    for (int i = s->snakel - 1; i > 0; i--)
    {
        s->snake[i] = s->snake[i - 1];
    }
    switch (s->fx)
    {
    case Fx::left:
        s->snake[0].x -= 1; // 向左移动
        break;
    case Fx::right:
        s->snake[0].x += 1; // 向右移动
        break;
    case Fx::top:
        s->snake[0].y -= 1; // 向上移动
        break;
    case Fx::down:
        s->snake[0].y += 1; // 向下移动
        break;
    default:
        break;
    }
}
// 光标隐藏
void hideCursor()
{
    printf("\033[2J\033[H"); // 清屏 + 光标移动到左上角
}
// 画地图
void Drawmap(Map *map)
{
    cout << "┏";
    for (int x = 0; x < W; ++x)
    {
        cout << "━";
    }
    cout << "┓" << endl;

    for (int y = 0; y < H; ++y)
    {
        cout << "┃";
        for (int x = 0; x < W; ++x)
        {
            if (map->data[y][x] == Gezitype::EMPTY)
                cout << " ";
            else if (map->data[y][x] == Gezitype::FOOD)
                cout << "*";
            else if (map->data[y][x] == Gezitype::SNAKE)
                cout << "O";
        }
        cout << "┃" << endl;
    }
    cout << "┗";
    for (int x = 0; x < W; ++x)
    {
        cout << "━";
    }
    cout << "┛" << endl;
    cout.flush();
}
// 键盘移动
void getDirection(Snake *s)
{
    char ch;
    if (read(STDIN_FILENO, &ch, 1) == 1)
    {
        switch (ch)
        {
        case 'w':
            if (s->fx != Fx::down)
                s->fx = Fx::top;
            break;
        case 's':
            if (s->fx != Fx::top)
                s->fx = Fx::down;
            break;
        case 'a':
            if (s->fx != Fx::right)
                s->fx = Fx::left;
            break;
        case 'd':
            if (s->fx != Fx::left)
                s->fx = Fx::right;
            break;
        }
    }
}

int main()
{
    initTermios();
    Map map;
    Snake s;
    Snakeinit(&s);
    Mapinit(&map);
    Setsnake(&s, &map);
    Drawmap(&map);
    hideCursor();
    while (1)
    {
        getDirection(&s);
        Mapinit(&map);
        Movesnake(&s);
        if (s.CheckCollision())
        {
            cout << "游戏结束! 最终长度: " << s.snakel << endl;
            break;
        }
        Setsnake(&s, &map);
        Drawmap(&map);
        hideCursor();
        usleep(200000);
    }
}