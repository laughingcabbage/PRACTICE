SELECT deptno
	  ,ename
	  ,sal
	  ,SUM(sal) OVER() AS TOTAL_SAL
	  ,TO_CHAR(sal/SUM(sal) OVER()*100, '9999.99') AS "%"
FROM emp
ORDER BY "%" DESC;

--    DEPTNO ENAME             SAL  TOTAL_SAL %
------------ ---------- ---------- ---------- --------
--        10 KING             5000      31525    15.86
--        10 Tiger            3600      31525    11.42
--        20 FORD             3000      31525     9.52
--        10 Cat              3000      31525     9.52
--        20 JONES            2975      31525     9.44
--        30 BLAKE            2850      31525     9.04
--        10 CLARK            2450      31525     7.77
--        30 ALLEN            1600      31525     5.08
--        30 TURNER           1500      31525     4.76
--        10 MILLER           1300      31525     4.12
--        30 WARD             1250      31525     3.97
--        30 MARTIN           1250      31525     3.97
--        30 JAMES             950      31525     3.01
--        20 SMITH             800      31525     2.54