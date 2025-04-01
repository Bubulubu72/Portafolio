


-- 1ERA - �Cu�les son los tres alimentos m�s vendidos?
SELECT TOP 3 a.NombreAlimento, SUM(oa.Cantidad) AS TotalVendido
FROM ORDEN_ALIMENTO oa
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
GROUP BY a.NombreAlimento
ORDER BY TotalVendido DESC

-- 2DA - �Cu�les son los alimentos con el precio unitario m�s alto?
SELECT NombreAlimento, PrecioUnitario
FROM ALIMENTO
ORDER BY PrecioUnitario DESC

-- 3ERA - �Cu�l es la categor�a de alimentos m�s vendida?
SELECT TOP 1 c.DescripcionCategoria, SUM(oa.Cantidad) AS TotalVendidos
FROM ORDEN_ALIMENTO oa
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
JOIN CATEGORIA c ON a.Categoria_Fk = c.IdCategoria
GROUP BY c.DescripcionCategoria
ORDER BY TotalVendidos DESC


-- 4TA - �Cu�les son los alimentos con el mayor costo unitario?
SELECT TOP 3 NombreAlimento, CostoUnitario
FROM ALIMENTO
ORDER BY CostoUnitario DESC

-- 5TA - �Cu�l es el alimento m�s caro vendido en una orden?
SELECT TOP 1 a.NombreAlimento, MAX(oa.PrecioLinea) AS PrecioMayor
FROM ORDEN_ALIMENTO oa
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
GROUP BY a.NombreAlimento
ORDER BY PrecioMayor DESC

-- 6TA - �Cu�les son los alimentos que pertenecen la categor�a Pizza?
SELECT a.NombreAlimento
FROM ALIMENTO a
JOIN CATEGORIA c ON a.Categoria_Fk = c.IdCategoria
WHERE c.DescripcionCategoria = 'Pizza' 

-- 7MA - �Cu�les son los tres proveedores que m�s ingresos han generado en ventas?
SELECT TOP 3 p.NombreProveedor, SUM(oa.PrecioLinea) AS IngresoTotal
FROM ORDEN_ALIMENTO oa
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
GROUP BY p.NombreProveedor
ORDER BY IngresoTotal DESC

-- 8VA - �Cu�ntas �rdenes de compra han sido realizadas por cada proveedor?
SELECT p.NombreProveedor, COUNT(DISTINCT o.IdOrden) AS TotalOrdenes
FROM ORDEN_ALIMENTO oa
JOIN ORDEN o ON oa.IdOrden = o.IdOrden
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
GROUP BY p.NombreProveedor
ORDER BY TotalOrdenes DESC

-- 9NA - �Cu�l es el promedio del costo unitario por proveedor?
SELECT p.NombreProveedor, AVG(a.CostoUnitario) AS PromedioCosto
FROM ALIMENTO a
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
GROUP BY p.NombreProveedor
ORDER BY PromedioCosto DESC

-- 10MA - �Cu�ntos alimentos distintos ha proporcionado cada proveedor?
SELECT p.NombreProveedor, COUNT(DISTINCT a.IdAlimento) AS AlimentosDistintos
FROM ALIMENTO a
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
GROUP BY p.NombreProveedor
ORDER BY AlimentosDistintos DESC

-- 11VA - �Cu�l es la categor�a de alimentos m�s proporcionada por cada proveedor?
SELECT p.NombreProveedor, c.DescripcionCategoria, COUNT(a.IdAlimento) AS TotalAlimentos
FROM ALIMENTO a
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
JOIN CATEGORIA c ON a.Categoria_Fk = c.IdCategoria
GROUP BY p.NombreProveedor, c.DescripcionCategoria
ORDER BY p.NombreProveedor DESC

-- 12VA - �Qu� proveedor ha proporcionado el mayor n�mero de alimentos en total? 
SELECT p.NombreProveedor, SUM(oa.Cantidad) AS TotalAlimentos
FROM ORDEN_ALIMENTO oa
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
GROUP BY p.NombreProveedor
ORDER BY TotalAlimentos DESC

-- 13VA - �Cu�l es el total de ingresos por cada proveedor en un a�o espec�fico?
SELECT p.NombreProveedor, YEAR(o.Fecha) AS A�o, SUM(oa.PrecioLinea) AS IngresoTotal
FROM ORDEN_ALIMENTO oa
JOIN ORDEN o ON oa.IdOrden = o.IdOrden
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
WHERE YEAR(o.Fecha) = 2021 --Cambiar A�o
GROUP BY p.NombreProveedor, Fecha

-- 14VA - �Cu�l es el proveedor que ha generado el menor ingreso en ventas
SELECT p.NombreProveedor, SUM(oa.PrecioLinea) AS IngresoTotal
FROM ORDEN_ALIMENTO oa
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
GROUP BY p.NombreProveedor
ORDER BY IngresoTotal ASC

-- 15VA - �Cu�ntos alimentos han sido comprados a cada proveedor por mes?
SELECT p.NombreProveedor, MONTH(o.Fecha) AS Mes, SUM(oa.Cantidad) AS TotalAlimentos
FROM ORDEN_ALIMENTO oa
JOIN ORDEN o ON oa.IdOrden = o.IdOrden
JOIN ALIMENTO a ON oa.IdAlimento = a.IdAlimento
JOIN PROVEEDOR p ON a.Proveedor_Fk = p.IdProveedor
WHERE MONTH(o.Fecha) = 9
GROUP BY p.NombreProveedor, Fecha
ORDER BY p.NombreProveedor

-- 16VA - �Cu�les son los alimentos m�s baratos en la categor�a Bebida?
SELECT a.NombreAlimento, a.PrecioUnitario
FROM ALIMENTO a
JOIN CATEGORIA c ON a.Categoria_Fk = c.IdCategoria
WHERE c.DescripcionCategoria = 'Bebida'
ORDER BY a.PrecioUnitario ASC

-- 17VA - �Cu�l es el precio promedio por alimento?
SELECT NombreAlimento, AVG(PrecioUnitario) AS PrecioPromedio
FROM ALIMENTO
GROUP BY NombreAlimento

