SELECT  SUM(sale_price) AS total_sales
FROM sales
WHERE EXTRACT(YEAR FROM sale_date) = 2025 and  EXTRACT(MONTH FROM sale_date) =1 ;
