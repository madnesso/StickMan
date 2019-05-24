# -*- coding: utf-8 -*-

import scrapy
import json
import ast
import re
import requests


def isJson(url):
    try:
        json_object = json.loads(requests.get(url).content)
    except ValueError:
        return False
    return True

class Mawdoo3(scrapy.Spider):
    name = "Mawdoo3"
    allowed_domains = ["mawdoo3.com"]
    start_urls = ["https://mawdoo3.com"]
    
    
    
    def parse(self, response):
        dataList = {}
        for category in response.css("div.category"):
            categoryTitle = category.css("h2.category-title > a > span.title::text").extract_first()
            dataList["categoryTitle"] = categoryTitle

            for categoryItem in response.css("ul.category-items > li"):
                itemTitle = categoryItem.css("a::text").extract_first()
                dataList["itemTitle"] = itemTitle

                for i in range(1, 9999999):
                    itemUrl = response.urljoin(
                        "https://mawdoo3.com/index.php?action=ajax&rs=JsonCategories&rsargs[]="+str(i)+"&rsargs[]="+str(itemTitle)
                    )
                    if not isJson(itemUrl):break
                    yield scrapy.Request(url=itemUrl, callback=self.parseBoxes, meta={"dataList": dataList})


    def parseBoxes(self, response):
        boxesData = ast.literal_eval(response.text)["Articles"]
        for box in boxesData:
            boxUrl = response.urljoin(box["url"])
            yield scrapy.Request(url=boxUrl, callback=self.parseArticles, meta={"dataList": response.meta.get("dataList")})

    def parseArticles(self, response):
        dataList = response.meta.get("dataList")
        articleTitle = response.css("[itemprop='headline']::text").extract_first()
        articleImage = response.css("[itemprop='image']::attr(src)").extract_first()
        articleDate = response.css("[itemprop='dateModified']::text").extract_first()
        articleAuthor = response.css("[itemprop='author']::text").extract_first()
        articleContent = re.sub(r"id=\"[^\"]*\"", "",
            "".join(
                response.xpath("//div[@class='mw-content-rtl']/*[local-name()!='script' and not(contains(@class, 'toc'))]").extract()
            )
        )
        
        dataList["articleTitle"] = articleTitle
        dataList["articleImage"] = articleImage
        dataList["articleDate"] = articleDate
        dataList["articleAuthor"] = articleAuthor
        dataList["articleContent"] = articleContent
        
        yield dataList