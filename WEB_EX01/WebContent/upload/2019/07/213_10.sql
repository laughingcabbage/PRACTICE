SELECT deptno
	  ,ename
	  ,sal
	  ,SUM(sal) OVER(PARTITION BY deptno) AS SUM_DEPT
	  ,TO_CHAR(sal/SUM(sal) OVER(PARTITION BY deptno)*100, '9999.99') AS "%"
FROM emp
ORDER BY deptno, ename;

--    DEPTNO ENAME             SAL   SUM_DEPT %
------------ ---------- ---------- ---------- --------
--        10 CLARK            2450      15350    15.96
--        10 Cat              3000      15350    19.54
--        10 KING             5000      15350    32.57
--        10 MILLER           1300      15350     8.47
--        10 Tiger            3600      15350    23.45
--        20 FORD             3000       6775    44.28
--        20 JONES            2975       6775    43.91
--        20 SMITH             800       6775    11.81
--        30 ALLEN            1600       9400    17.02
--        30 BLAKE            2850       9400    30.32
--        30 JAMES             950       9400    10.11
--        30 MARTIN           1250       9400    13.30
--        30 TURNER           1500       9400    15.96
--        30 WARD             1250       9400    13.30