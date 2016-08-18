package com.snail.utils.hbase;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.filter.Filter;

import com.snail.architecture.entity.CacheConstants;

/**
 * 用于缓存key生成的工具类<br/>
 * 推荐所有的生成缓存key方法命名都以generate字符串开头加上key业务名称，再以key字符串结尾<br/>
 * 缓存key的命名推荐以所处业务包、能唯一标识缓存key的业务名称、缓存key的业务参数等组成的字符串<br/>
 * 可以参考{@link #generateVipMerchantIdsKey()}方法
 * 
 * @author  on 2013.7.11
 */
public class CacheKeyUtil {

    private static final String CACHE_SEPERATE_CHAR                    = "-";
    private static final String CACHE_LOG_SEPARATE_CHAR                = " ";
    private static final String CACHE_UNDER_LINE_SEPERATE_CHAR         = "_";
    private static final String CACHE_NEW_KEY_PREFIX                   = "new";                                                                                 // 更换缓存key的标志
    private static final String USER_CATEGORY_PREFIX                   = "com.yhd.userProfile.explicity.impl.UserExplicityProfileImpl#user_category_prefix_";
    private static final String GUID_APPRECIATE_CATEGORYS_CACHE_PREFIX = "com.yhd.userProfile.explicity.impl.UserExplicityProfileImpl#GUIDAppreciateCategorys";

    /**
     * 根据网站ID查询所有的VIP商家ID的缓存key值
     * 
     * @param mcSiteId 网站ID
     * @return String 存储VIP商家的key值
     * @author  on 2013.7.11
     */
    public static String generateVipMerchantIdsKey(Long mcSiteId) {
        StringBuilder sb = new StringBuilder();
        sb.append("producttag");
        sb.append(CACHE_SEPERATE_CHAR);
        sb.append("vipMerchant");
        sb.append(CACHE_SEPERATE_CHAR);
        sb.append(mcSiteId);
        return sb.toString();
    }

    /**
     * 获取PMS_CONFIG的缓存Key值
     * 
     * @return String
     * @author  on 2014.6.19
     */
    public static String generatePmsConfigCacheKey() {
        StringBuilder sb = new StringBuilder();
        sb.append("pmsconfig");
        sb.append(CACHE_SEPERATE_CHAR);
        sb.append("all");
        return sb.toString();
    }
    
    public static String generatePmsConfigCacheKey(Long sectionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("pmsconfig");
        sb.append(CACHE_SEPERATE_CHAR);
        sb.append(sectionId);
        return sb.toString();
    }

    /**
     * 根据栏位ID、配置类型获取缓存Key值
     * 
     * @param sectionId
     * @param configType
     * @return String
     * @author  on 2014.6.19
     */
    public static String generatePmsMessConfigCacheKey(Long sectionId, Long configType) {
        StringBuilder sb = new StringBuilder();
        sb.append("pmsMessConfig");
        sb.append(CACHE_SEPERATE_CHAR);
        sb.append(sectionId);
        sb.append(CACHE_SEPERATE_CHAR);
        sb.append(configType);
        return sb.toString();
    }

    /**
     * 根据方法名，是否命中，以及缓存key，生成cache日志
     * 
     * @param get 是否命中
     * @param cacheKey 缓存key
     * @return String log日志字符串
     * @author  on 2014.6.19
     */
    public static final String generateCacheLog(boolean get, String cacheKey) {
        StringBuffer sb = new StringBuffer(cacheKey);
        sb.append(CACHE_LOG_SEPARATE_CHAR);
        if (get) {
            sb.append("get");
        } else {
            sb.append("miss");
        }
        return sb.toString();
    }

    /**
     * 生成用户喜爱的缓存key
     * 
     * @param userId
     * @return String
     * @author  on 2014.6.30
     */
    public static final String generateUserAppreciateCategorysCacheKey(Long userId) {
        StringBuffer sb = new StringBuffer(USER_CATEGORY_PREFIX);
        sb.append(CACHE_UNDER_LINE_SEPERATE_CHAR);
        sb.append(CACHE_NEW_KEY_PREFIX);
        sb.append(CACHE_UNDER_LINE_SEPERATE_CHAR);
        sb.append(userId);
        return sb.toString();
    }

    /**
     * 根据guid生成查询喜好的分类缓存key
     * 
     * @param guID
     * @return String
     * @author  on 2014.6.30
     */
    public static final String generateGUIDAppreciateCategorysCacheKey(String guid) {
        StringBuffer sb = new StringBuffer(GUID_APPRECIATE_CATEGORYS_CACHE_PREFIX);
        sb.append(CACHE_UNDER_LINE_SEPERATE_CHAR);
        sb.append(guid);
        return sb.toString();
    }

    /**
     * 生成产品基因宝宝年龄段的缓存key
     * 
     * @param productId 产品ID
     * @return {@link String}
     * @author  on 2014.8.11
     */
    public static String generateProductBabaAgeRange(Long productId) {
        StringBuilder sb = new StringBuilder();
        sb.append("product_revision");
        sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
        sb.append("productBabyAge");
        sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
        if (productId != null) {
            sb.append(productId);
        }
        return sb.toString();
    }

    /**
     * 根据表名、行、列族、列名生成查询hbase的缓存key
     * 
     * @param tableName
     * @param row
     * @param family
     * @param qualifier
     * @return {@link String}
     * @author  on 2014.8.11
     */
    public static String generateHBaseQueryCacheKey(String tableName, byte[] row, byte[] family, byte[] qualifier) {
        StringBuilder sb = new StringBuilder();
        sb.append("haseQuery");
        if(StringUtils.isNotBlank(tableName)){
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(tableName);
        }
        if (row != null && row.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(row));
        }
        if (family != null && family.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(family));
        }
        if (qualifier != null && qualifier.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(qualifier));
        }
        return sb.toString();
    }

    /**
     * 根据表名、行key等构建查询结果缓存key,包含{@link Filter}
     * 
     * @param tableName
     * @param row
     * @param family
     * @param qualifier
     * @param filters
     * @return {@link String}
     * @author  on 2014.8.12
     */
    public static String generateHBaseQueryCacheKeyWithFilter(String tableName, byte[] row, byte[] family,
                                                              byte[] qualifier, List<Filter> filters) {
        StringBuilder sb = new StringBuilder();
        sb.append("haseQueryWithFilter");
        if(StringUtils.isNotBlank(tableName)){
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(tableName);
        }
        if (row != null && row.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(row));
        }
        if (family != null && family.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(family));
        }
        if (qualifier != null && qualifier.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(qualifier));
        }
        if (!CollectionUtils.isEmpty(filters)) {
            for (Filter filter : filters) {
                sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
                sb.append(filter.toString());
            }
        }
        return sb.toString();
    }

    /**
     * 构建按照单行多列的查询缓存key
     * 
     * @param tableName
     * @param row
     * @param family
     * @param qualifierList
     * @param filters
     * @return {@link String}
     * @author  on 2014.8.12
     */
    public static String generateHBaseQueryCacheKeyWithFilterAndMultiQualifier(String tableName, byte[] row,
                                                                               byte[] family,
                                                                               List<byte[]> qualifierList,
                                                                               List<Filter> filters) {
        StringBuilder sb = new StringBuilder();
        sb.append("multiHaseQuery");
        if(StringUtils.isNotBlank(tableName)){
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(tableName);
        }
        if (row != null && row.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(row));
        }
        if (family != null && family.length > 0) {
            sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
            sb.append(new String(family));
        }
        if (!CollectionUtils.isEmpty(qualifierList)) {
            for (byte[] qualifier : qualifierList) {
                if (qualifier == null || qualifier.length == 0) {
                    continue;
                }
                sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
                sb.append(new String(qualifier));
            }
        }
        if (!CollectionUtils.isEmpty(filters)) {
            for (Filter filter : filters) {
                if (filter == null) {
                    continue;
                }
                sb.append(CacheConstants.CACHE_UNDER_LINE_SEPERATE_CHAR);
                sb.append(filter.toString());
            }
        }
        return sb.toString();
    }

}
