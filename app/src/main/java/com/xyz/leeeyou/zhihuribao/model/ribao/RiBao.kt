package com.xyz.leeeyou.zhihuribao.model.ribao

/**
 * Created by leeeyou on 2017/4/21.
 *
 * getLatestRiBao返回的数据结构
 *
 * 数据类是一种非常强大的类，它可以让你避免创建Java中的用于保存状态但又操作非常简单的POJO的模版代码。
 * 它们通常只提供了用于访问它们属性的简单的getter 和setter。
 *
 * 我们常常会创建一个只包含数据的类，其他什么事情都不做。
 * 在Kotlin中，这样的类叫做数据类，表示关键字为data:
 * data class User(val name: String, val age: Int)
 * 加上data,可以转成string内容，不加的话获取的我就是RiBao的地址
 */
data class RiBao(var date: String, var stories: List<Story>)