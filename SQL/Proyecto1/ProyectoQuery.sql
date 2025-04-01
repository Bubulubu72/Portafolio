
SELECT * FROM [Cafeteria_Rest].[dbo].ORDEN_ALIMENTO

select * from [Cafeteria_Rest].[dbo].[ALIMENTO]
 
SELECT * FROM [Cafeteria_Rest].[dbo].ORDEN


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





--/////////////// Poblar Proyecto /////////////////--

SELECT 
		DISTINCT
		CAST(CONVERT(varchar(8),[Fecha], 112) AS bigint  ) AS TimeID,
		YEAR ( [Fecha] ) as Year, 
		MONTH ([Fecha]) AS Month,
		DAY([Fecha]) AS Day,
		DATENAME (MM, [Fecha]) AS MesLetra,
		DATENAME (dw, [Fecha]) AS DiaSemana,
		[Fecha],
	case
			when month ([Fecha]) in (1,2,3) then 'T1'
			when month ([Fecha]) in (4,5,6) then 'T2'
			when month ([Fecha]) in (7,8,9) then 'T3'
			else 'T4'
		end,
	case
		when month ([Fecha]) in (1,2,3,4,5,6) then 'H1'
		else 'H2'
	end
FROM [dbo].[ORDEN]

select 
	C.IdCajero,
	OA.IdAlimento,
	O.IdOrden,
	CAST(CONVERT(varchar(8), O.Fecha, 112) AS int),
	O.TotalOrden,
	O.Propina,
	OA.Cantidad,
	OA.PrecioLinea,
	[PrecioUnitario]-[CostoUnitario] as Utilidad,
	COUNT(O.[IdOrden]) as unknown
from [Cafeteria_Rest].[dbo].[CAJERO] C
INNER JOIN  [Cafeteria_Rest].[dbo].[ORDEN] O ON O.[Cajero_Fk] = C.IdCajero
INNER JOIN  [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] OA ON OA.IdOrden = O.IdOrden 
INNER JOIN  [Cafeteria_Rest].[dbo].[ALIMENTO] A on A.IdAlimento = OA.IdAlimento
GROUP BY 
	C.IdCajero,
	OA.IdAlimento,
	O.IdOrden,
	CAST(CONVERT(varchar(8), O.Fecha, 112) AS int),
	O.TotalOrden,
	O.Propina,
	OA.Cantidad,
	OA.PrecioLinea,
	[PrecioUnitario]-[CostoUnitario]
 

 /*INTENTO POBLAR BD PROYECTO*/

 /*DIM_CAJERO*/ 
 INSERT INTO [Proyecto].[dbo].[DIM_CAJERO]
 SELECT c.IdCajero,
		c.NombreCajero,
		c.ApellidoCajero,
		caf.IdCafeteria,
		caf.NombreCafeteria
 FROM [Cafeteria_Rest].[dbo].CAJERO AS c
 JOIN [Cafeteria_Rest].[dbo].CAFETERIA as caf on caf.IdCafeteria = c.Cafeteria_Fk

 SELECT * FROM [Proyecto].[dbo].[DIM_CAJERO]

 /*DIM_CATEGORIA*/
 INSERT INTO [Proyecto].[dbo].[DIM_CATEGORIA]
 SELECT cat.IdCategoria,
		c.IdClasificacion,
		cat.DescripcionCategoria,
		c.NombreClasificacion
 FROM [Cafeteria_Rest].[dbo].CATEGORIA AS cat
 JOIN [Cafeteria_Rest].[dbo].CLASIFICACION AS c ON c.IdClasificacion = cat.Clasificacion_Fk

  SELECT * FROM [Proyecto].[dbo].[DIM_CATEGORIA]

/*DIM_ALIMENTO*/
INSERT INTO [Proyecto].[dbo].[DIM_ALIMENTO]
SELECT a.IdAlimento,
	   a.NombreAlimento
FROM [Cafeteria_Rest].[dbo].[ALIMENTO] AS a

SELECT * FROM [Proyecto].[dbo].DIM_ALIMENTO

/*SUB_ORDEN*/
INSERT INTO [Proyecto].[dbo].[SUB_ORDEN]
SELECT IdOrden
FROM [Cafeteria_Rest].[dbo].[ORDEN]

SELECT * FROM [Proyecto].[dbo].SUB_ORDEN

/*DIM_Fecha*/
INSERT INTO [Proyecto].[dbo].[DIM_TIEMPO]
	SELECT 
			DISTINCT
			CAST(CONVERT(varchar(8),[Fecha], 112) AS bigint  ) AS Fecha,
			YEAR ([Fecha]) AS Año, 
			MONTH ([Fecha]) AS Mes,
			DAY ([Fecha]) AS Dia,
			DATEPART(WEEK, [Fecha]) AS Semana,
			case
				when month ([Fecha]) in (1,2,3) then 'T1'
				when month ([Fecha]) in (4,5,6) then 'T2'
				when month ([Fecha]) in (7,8,9) then 'T3'
				else 'T4'
			end,
			case
				when month ([Fecha]) in (1,2,3,4,5,6) then 'H1'
				else 'H2'
			end
		FROM [Cafeteria_Rest].[dbo].[ORDEN]

SELECT * FROM [Proyecto].[dbo].DIM_TIEMPO

/*FACT_ORDEN*/

INSERT INTO [Proyecto].[dbo].[FACT_ORDEN]
SELECT 
    O.IdOrden,                     
    OA.IdAlimento,                 
    O.Cajero_Fk AS CajeroID,      
    CAST(CONVERT(varchar(8),[Fecha], 112) AS bigint  ) AS FechaID,
    A.Categoria_Fk AS CategoriaID, 
    O.Propina,                     
    O.Factura,                     
    (OA.Cantidad * A.PrecioUnitario) - (OA.Cantidad * A.CostoUnitario) AS Utilidad, 
    OA.Cantidad,                   
    A.PrecioUnitario,              
    A.CostoUnitario,               
    OA.PrecioLinea                 
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ORDEN] AS O ON O.IdOrden = OA.IdOrden
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON A.IdAlimento = OA.IdAlimento

DELETE FROM [Proyecto].[dbo].FACT_ORDEN

SELECT * FROM [Proyecto].[dbo].FACT_ORDEN


/*////////////////  PROVEEDOR   //////////////// */

SELECT * FROM [Cafeteria_Rest].[dbo].[PROVEEDOR]


SELECT * FROM [Cafeteria_Rest].[dbo].ALIMENTO as a
JOIN [Cafeteria_Rest].[dbo].PROVEEDOR as p on p.IdProveedor = a.Proveedor_Fk


-- Poblar Proveedor --


INSERT INTO [Proyecto].[dbo].DIM_PROVEEDOR
select	IdProveedor,
		NombreProveedor
from [Cafeteria_Rest].[dbo].[PROVEEDOR]

SELECT * FROM DIM_PROVEEDOR

-- FACT_VENTAS --

INSERT INTO [Proyecto].[dbo].FACT_VENTAS
SELECT	O.IdOrden,                     
		OA.IdAlimento,
		P.IdProveedor,      
		A.Categoria_Fk AS CategoriaID,                     
		CAST(CONVERT(varchar(8),[Fecha], 112) AS bigint  ) AS FechaID,
		OA.Cantidad,                   
		A.PrecioUnitario,              
		A.CostoUnitario                            
FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
JOIN [Cafeteria_Rest].[dbo].[ORDEN] AS O ON O.IdOrden = OA.IdOrden
JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON A.IdAlimento = OA.IdAlimento
JOIN [Cafeteria_Rest].[dbo].[PROVEEDOR] AS P ON P.IdProveedor = A.Proveedor_Fk

SELECT * FROM [Proyecto].[dbo].FACT_VENTAS

DELETE FROM [Proyecto].[dbo].FACT_VENTAS