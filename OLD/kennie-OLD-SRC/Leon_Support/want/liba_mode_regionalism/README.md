# 中国行政区域模块

> 数据基于[中国统计局行政区划代码](http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/).

## 模块使用

为了简化使用, 该模块提供`RegionHelper`类向外暴露操作接口. 使用时需要按照以下次序执行.

1. 初始化

        RegionHelper.init(context);
        
2. 在使用时调用方法接口, 如:

        // 获取所有的省份, 自治区, 直辖市
        final RegionHelper helper = RegionHelper.getInstance();
        final List<Region> provinces = helper.getProvinces();

## 方法说明

该模块提供以下常用方法:

| 方法 | 描述 | 
| --- | --- | 
| init(context) | 模块初始化. 该方法需要保证首先被调用. |
| getInstance() | 获取模块单例, 执行此方法之前需要先执行`init(context)`方法. |
| getProvinces() | 获取所有省份 |
| getProvince(region) | 根据城市或区县查询对应的省份 |
| getCitys(region) | 根据省份获取对应的所有城市 |
| getCity(region) | 根据区县查询对应的省份 |
| getDistricts(region) | 根据城市获取对应的所有区县 |