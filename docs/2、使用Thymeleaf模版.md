# 《Spring in Action》4th --第6章 渲染WEB视图

## 前言


## 使用Thymeleaf视图解析器
在路径上使用标签th:href和@{}来表达
```
th:href="@{/resources/style.css}"
```

## 问题

在home.html中，由于<meta charset="UTF-8">没有结束符，导致报错：
```
org.xml.sax.SAXParseException; lineNumber: 10; columnNumber: 3; 元素类型 "meta" 必须由匹配的结束标记 "</meta>" 终止。
```
原因是：thymeleaf模板引擎默认是Template modes:HTML5解析的，所以解析比较严格。
解决：
```
<meta charset="UTF-8"/>
```

