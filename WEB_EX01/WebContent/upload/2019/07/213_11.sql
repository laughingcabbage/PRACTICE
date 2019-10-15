SELECT l_date AS 대출일자
	  ,l_code AS 대출종목코드
	  ,l_qty AS 대출건수
	  ,l_total AS 대출총액
	  ,SUM(l_total) over(ORDER by l_date) AS 누적대출금액
FROM loan
WHERE l_store=1000
ORDER BY l_date;