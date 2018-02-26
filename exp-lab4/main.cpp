#include<cstdio>
#include<cstdlib>
#include<iostream>
#include<stack>
#include <string.h>
using namespace std;
#define N 100

int i=0;
char a[N]= {'A','B','#','C','E','#','F','#','#','D','#','#','X','#','#','Y','Z','G','H','#','I','#','#','#'};
typedef struct TREE_NODE
{
    char data;
    struct TREE_NODE *lchild;
    struct TREE_NODE *rchild;
    int st;
    int en;
}*tree_node;
int S=1;
int e=1;

tree_node createTree()//树的建立
{
    char ch;
    tree_node t;
    while(a[i]!='\0')
    {
        ch=a[i];
        i++;
        if(ch=='#')  //判断二叉树是否为空
            t=NULL;
        else
        {
            t=(TREE_NODE* )malloc(sizeof(TREE_NODE));  //二叉树的生成
            t->data=ch;
            t->lchild=createTree();
            t->rchild=createTree();
        }
        return t;
    }
}

void preOrder(tree_node p) //先序遍历
{
    stack<tree_node> s;
    while(p!=NULL || !s.empty())
    {
        while(p!=NULL)
        {
            cout<<p->data<<": ";
            cout<<p->st<<",";
            cout<<p->en<<endl;
            s.push(p);
            p = p->lchild;
        }
        if(!s.empty())
        {
            p = s.top();
            s.pop();
            p = p->rchild;
        }
    }
}

void order(tree_node p)
{
    stack<tree_node> s;
    while(p!=NULL || !s.empty())
    {
        while(p!=NULL)
        {
            p->st = S++;
            e++;
            s.push(p);
            p = p->lchild;
        }
        if(!s.empty())
        {
            p = s.top();
            p->en = e++;
            S++;
            s.pop();
            p = p->rchild;
        }
    }
}
int main()
{
    int flag=1;
    tree_node t;
    t=createTree();
    order(t);
    // cout << t->data;
    preOrder(t);
    return 0;
}
