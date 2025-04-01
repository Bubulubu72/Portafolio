
SELECT OA.IdOrden, 
		A.IdAlimento, 
		A.NombreAlimento, 
		OA.Cantidad, 
		A.PrecioUnitario,
		OA.PrecioLinea,
		A.CostoUnitario,
		((OA.Cantidad * A.PrecioUnitario) - (OA.Cantidad * A.CostoUnitario)) as Utilidad
From  [Cafeteria_Rest].[dbo].[ALIMENTO] as A
JOIN [Cafeteria_Rest].[dbo].ORDEN_ALIMENTO as OA on OA.IdAlimento = A.IdAlimento
JOIN [Cafeteria_Rest].[dbo].ORDEN as O on O.IdOrden = OA.IdOrden


SELECT DISTINCT OA.IdOrden,
		O.TotalOrden AS Total_Venta,
		O.Factura,
		C.IdCajero,
		C.NombreCajero,
		O.Propina,
		SUM(A.CostoUnitario * OA.Cantidad) as TotalCosto,
		SUM(((OA.Cantidad * A.PrecioUnitario) - (OA.Cantidad * A.CostoUnitario))) as Utilidad
FROM [Cafeteria_Rest].[dbo].ORDEN_ALIMENTO as OA
JOIN [Cafeteria_Rest].[dbo].ORDEN as O on O.IdOrden = OA.IdOrden
JOIN [Cafeteria_Rest].[dbo].CAJERO as C on C.IdCajero = O.Cajero_Fk
JOIN [Cafeteria_Rest].[dbo].ALIMENTO as A on A.IdAlimento = OA.IdAlimento
GROUP BY OA.IdOrden,
		 O.TotalOrden, 
         O.Factura, 
         C.IdCajero, 
         C.NombreCajero, 
         O.Propina;


-- PREGUNTAS --

--1ERA - ¿Cuál es total de ventas por alimento?
SELECT 
    A.NombreAlimento,           
    SUM(OA.PrecioLinea) AS TotalVentas 
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON A.IdAlimento = OA.IdAlimento
GROUP BY A.NombreAlimento             
ORDER BY TotalVentas ASC;     

--2DA - ¿Cuál es total de ventas por cajero?
SELECT 
	c.NombreCajero,
	 SUM(oa.PrecioLinea) AS TotalVentas
FROM [Cafeteria_Rest].[dbo].CAJERO as c
JOIN [Cafeteria_Rest].[dbo].ORDEN AS o on o.Cajero_Fk = c.IdCajero
JOIN [Cafeteria_Rest].[dbo].ORDEN_ALIMENTO as oa on oa.IdOrden = o.IdOrden
GROUP BY c.NombreCajero             
ORDER BY TotalVentas ASC;  

--3ERA - ¿Cuáles son los totales facturados y no facturados?
SELECT 
    CASE 
        WHEN O.Factura IS NOT NULL THEN 'Facturado' 
        ELSE 'No Facturado' 
    END AS EstadoFactura,   
    SUM(O.TotalOrden) AS TotalVentas 
FROM [Cafeteria_Rest].[dbo].[ORDEN] AS O
GROUP BY 
    CASE 
        WHEN O.Factura IS NOT NULL THEN 'Facturado' 
        ELSE 'No Facturado' 
    END
ORDER BY 
    EstadoFactura DESC;  

--4TA - ¿Qué cajero tiene más ventas?, pero ¿Cuál más utilidades?
SELECT 
    C.NombreCajero, 
    C.ApellidoCajero,
    SUM(OA.PrecioLinea) AS TotalVentas,
	SUM(((OA.Cantidad * A.PrecioUnitario) - (OA.Cantidad * A.CostoUnitario))) as Utilidad
FROM [Cafeteria_Rest].[dbo].[ORDEN] AS O
JOIN [Cafeteria_Rest].[dbo].[CAJERO] AS C ON C.IdCajero = O.Cajero_Fk
JOIN [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA ON OA.IdOrden = O.IdOrden
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON A.IdAlimento = OA.IdAlimento
GROUP BY C.NombreCajero, 
    C.ApellidoCajero
ORDER BY TotalVentas DESC;

-- 5TA - ¿Top 5 de los alimentos que más vende cada uno de los cajeros?
WITH CajeroVentas AS (
    SELECT 
        C.NombreCajero,
        A.NombreAlimento,
        SUM(OA.Cantidad) AS TotalCantidad
    FROM [Cafeteria_Rest].[dbo].[ORDEN] O
    JOIN [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] OA ON O.IdOrden = OA.IdOrden
    JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] A ON OA.IdAlimento = A.IdAlimento
    JOIN [Cafeteria_Rest].[dbo].[CAJERO] C ON O.Cajero_Fk = C.IdCajero
    GROUP BY 
        C.NombreCajero,
        A.NombreAlimento
)
SELECT 
    NombreCajero, 
    NombreAlimento, 
    TotalCantidad
FROM 
    (SELECT 
         NombreCajero, 
         NombreAlimento, 
         TotalCantidad,
         ROW_NUMBER() OVER (PARTITION BY NombreCajero ORDER BY TotalCantidad DESC) AS RankVentas
     FROM CajeroVentas
    ) AS CajeroAlimentos
WHERE 
    RankVentas <= 5
ORDER BY 
    NombreCajero, RankVentas;


--6TA - ¿Cuántas órdenes existen por periodo de tiempo?

-- DIA -- 
SELECT 
    CAST(O.Fecha AS DATE) AS Dia, 
    COUNT(O.IdOrden) AS TotalOrdenes
FROM [Cafeteria_Rest].[dbo].[ORDEN] AS O
GROUP BY 
    CAST(O.Fecha AS DATE)
ORDER BY 
    Dia;

-- MES --
SELECT 
    YEAR(O.Fecha) AS Año, 
    MONTH(O.Fecha) AS Mes, 
    COUNT(O.IdOrden) AS TotalOrdenes
FROM 
    [Cafeteria_Rest].[dbo].[ORDEN] AS O
GROUP BY 
    YEAR(O.Fecha), 
    MONTH(O.Fecha)
ORDER BY 
    Año, Mes;

-- Trimestre --
SELECT 
    YEAR(O.Fecha) AS Año, 
    DATEPART(QUARTER, O.Fecha) AS Trimestre, 
    COUNT(O.IdOrden) AS TotalOrdenes
FROM 
    [Cafeteria_Rest].[dbo].[ORDEN] AS O
GROUP BY 
    YEAR(O.Fecha), 
    DATEPART(QUARTER, O.Fecha)
ORDER BY 
    Año, Trimestre;

-- AÑO --
SELECT 
    YEAR(O.Fecha) AS Año, 
    COUNT(O.IdOrden) AS TotalOrdenes
FROM [Cafeteria_Rest].[dbo].[ORDEN] AS O
GROUP BY 
    YEAR(O.Fecha)
ORDER BY 
    Año;

-- 7MA - ¿Cuál es el valor promedio de venta de las ordenes?
SELECT AVG(TotalOrden) as ValorPromedio
FROM [Cafeteria_Rest].[dbo].[ORDEN]

-- 8VA - ¿Cuál es el valor promedio de utilidad de las ordenes?
SELECT
		AVG(O.TotalOrden - ((OA.Cantidad * A.PrecioUnitario) - (OA.Cantidad * A.CostoUnitario))) AS UtilidadPromedio
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA 
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON A.IdAlimento = OA.IdAlimento
JOIN[Cafeteria_Rest].[dbo].ORDEN AS O ON O.IdOrden = OA.IdOrden

-- 9NA - ¿Los 5 alimentos más vendidos?
SELECT TOP 5 
    A.NombreAlimento, 
    SUM(OA.Cantidad) AS TotalCantidadVendida
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON OA.IdAlimento = A.IdAlimento
GROUP BY 
    A.NombreAlimento
ORDER BY 
    TotalCantidadVendida DESC;

-- 10MA -¿Los 5 alimentos menos vendidos?
SELECT TOP 5 
    A.NombreAlimento, 
    SUM(OA.Cantidad) AS TotalCantidadVendida
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON OA.IdAlimento = A.IdAlimento
GROUP BY 
    A.NombreAlimento
ORDER BY 
    TotalCantidadVendida ASC;

--11VA - ¿Cuáles alimentos dejan más costo?
SELECT 
    A.NombreAlimento,
	OA.Cantidad,
	A.CostoUnitario,
    SUM(OA.Cantidad * A.CostoUnitario) AS CostoTotal
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON OA.IdAlimento = A.IdAlimento
GROUP BY A.NombreAlimento, OA.Cantidad, A.CostoUnitario
ORDER BY CostoTotal DESC;

-- 12VA - ¿Cuáles alimentos dejan más utilidades?
SELECT 
    A.NombreAlimento, 
    SUM(OA.Cantidad * (A.PrecioUnitario - A.CostoUnitario)) AS UtilidadTotal
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON OA.IdAlimento = A.IdAlimento
GROUP BY A.NombreAlimento
ORDER BY UtilidadTotal DESC;

-- 13VA - ¿Cuáles alimentos dejan menos costo?
SELECT 
    A.NombreAlimento, 
    SUM(OA.Cantidad * A.CostoUnitario) AS CostoTotal
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON OA.IdAlimento = A.IdAlimento
GROUP BY A.NombreAlimento
ORDER BY CostoTotal ASC;

-- 14VA - ¿Cuáles alimentos dejan menos utilidades?
SELECT 
    A.NombreAlimento, 
    SUM(OA.Cantidad * (A.PrecioUnitario - A.CostoUnitario)) AS UtilidadTotal
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON OA.IdAlimento = A.IdAlimento
GROUP BY A.NombreAlimento
ORDER BY UtilidadTotal ASC;

-- 15VA - Representación por porcentaje del total de ventas por alimento.
WITH UtilidadesPorAlimento AS (
    SELECT 
        A.NombreAlimento, 
        SUM(OA.Cantidad * (A.PrecioUnitario - A.CostoUnitario)) AS UtilidadTotal
    FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
    JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON OA.IdAlimento = A.IdAlimento
    GROUP BY A.NombreAlimento
),
TotalUtilidad AS (
    SELECT 
        SUM(UtilidadTotal) AS TotalUtilidadGeneral
    FROM 
        UtilidadesPorAlimento
)
SELECT 
    U.NombreAlimento, 
    U.UtilidadTotal,
    (U.UtilidadTotal * 100.0 / T.TotalUtilidadGeneral) AS PorcentajeUtilidad
FROM 
    UtilidadesPorAlimento U, 
    TotalUtilidad T
ORDER BY 
    PorcentajeUtilidad DESC;