SELECT l_date AS ��������
	  ,l_code AS ���������ڵ�
	  ,l_qty AS ����Ǽ�
	  ,l_total AS �����Ѿ�
	  ,SUM(l_total) over(ORDER by l_date) AS ��������ݾ�
FROM loan
WHERE l_store=1000
ORDER BY l_date;