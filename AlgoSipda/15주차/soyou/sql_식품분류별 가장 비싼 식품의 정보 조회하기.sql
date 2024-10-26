SELECT fp.CATEGORY, mp.MAX_PRICE, fp.PRODUCT_NAME
FROM FOOD_PRODUCT as fp
         RIGHT JOIN (SELECT CATEGORY, MAX(PRICE) as MAX_PRICE
                     FROM FOOD_PRODUCT
                     WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
                     GROUP BY CATEGORY) as mp
                    ON mp.CATEGORY = fp.CATEGORY and mp.MAX_PRICE = fp.PRICE
ORDER BY fp.price desc;