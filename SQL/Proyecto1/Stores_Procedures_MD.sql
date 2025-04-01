

-- Store Poblar FACT_ORDEN
CREATE PROCEDURE POBLAR_FACT_ORDEN
AS
BEGIN
-- Iniciar transacción
    BEGIN TRANSACTION;

    BEGIN TRY

	/*DIM_CAJERO*/ 
	INSERT INTO [Proyecto].[dbo].[DIM_CAJERO]
	SELECT c.IdCajero,
		c.NombreCajero,
		c.ApellidoCajero,
		caf.IdCafeteria,
		caf.NombreCafeteria
	FROM [Cafeteria_Rest].[dbo].CAJERO AS c
	JOIN [Cafeteria_Rest].[dbo].CAFETERIA as caf on caf.IdCafeteria = c.Cafeteria_Fk

	/*DIM_CATEGORIA*/
	INSERT INTO [Proyecto].[dbo].[DIM_CATEGORIA]
	SELECT cat.IdCategoria,
		c.IdClasificacion,
		cat.DescripcionCategoria,
		c.NombreClasificacion
	FROM [Cafeteria_Rest].[dbo].CATEGORIA AS cat
	JOIN [Cafeteria_Rest].[dbo].CLASIFICACION AS c ON c.IdClasificacion = cat.Clasificacion_Fk

	 /*DIM_ALIMENTO*/
	INSERT INTO [Proyecto].[dbo].[DIM_ALIMENTO]
	SELECT a.IdAlimento,
		   a.NombreAlimento
	FROM [Cafeteria_Rest].[dbo].[ALIMENTO] AS a

	/*SUB_ORDEN*/
	INSERT INTO [Proyecto].[dbo].[SUB_ORDEN]
	SELECT IdOrden
	FROM [Cafeteria_Rest].[dbo].[ORDEN]

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
		OA.Cantidad * ( A.PrecioUnitario - A.CostoUnitario) AS Utilidad, 
		OA.Cantidad,                   
		A.PrecioUnitario,              
		A.CostoUnitario,               
		OA.PrecioLinea                 
	FROM [Cafeteria_Rest].[dbo].[ORDEN_ALIMENTO] AS OA
	JOIN [Cafeteria_Rest].[dbo].[ORDEN] AS O ON O.IdOrden = OA.IdOrden
	JOIN [Cafeteria_Rest].[dbo].[ALIMENTO] AS A ON A.IdAlimento = OA.IdAlimento

	-- Confirmar transacción si todo está correcto
        COMMIT TRANSACTION;

    END TRY
    BEGIN CATCH
        -- Deshacer la transacción en caso de error
        ROLLBACK TRANSACTION;
        -- Lanzar el error capturado
        THROW;
    END CATCH;
END;

--Pruebas 

EXEC POBLAR_FACT_ORDEN

SELECT * FROM [Proyecto].[dbo].DIM_CAJERO --5
DELETE FROM [Proyecto].[dbo].DIM_CAJERO

SELECT * FROM [Proyecto].[dbo].DIM_CATEGORIA --9
DELETE FROM [Proyecto].[dbo].DIM_CATEGORIA

SELECT * FROM [Proyecto].[dbo].DIM_ALIMENTO --31
DELETE FROM [Proyecto].[dbo].DIM_ALIMENTO

SELECT * FROM [Proyecto].[dbo].SUB_ORDEN --13,506
DELETE FROM [Proyecto].[dbo].SUB_ORDEN

SELECT * FROM [Proyecto].[dbo].DIM_TIEMPO --5,534
DELETE FROM [Proyecto].[dbo].DIM_TIEMPO 

SELECT * FROM [Proyecto].[dbo].FACT_ORDEN -- 104,282
DELETE FROM [Proyecto].[dbo].FACT_ORDEN


--STORE PROCEDURE FACT_PROVEEDOR

CREATE PROCEDURE POBLAR_FACT_PROVEEDOR
AS
BEGIN
-- Iniciar transacción
    BEGIN TRANSACTION;

    BEGIN TRY

	-- Poblar Proveedor --
	INSERT INTO [Proyecto].[dbo].DIM_PROVEEDOR
	SELECT	IdProveedor,
		NombreProveedor
	FROM [Cafeteria_Rest].[dbo].[PROVEEDOR]

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


	-- Confirmar transacción si todo está correcto
        COMMIT TRANSACTION;

    END TRY
    BEGIN CATCH
        -- Deshacer la transacción en caso de error
        ROLLBACK TRANSACTION;
        -- Lanzar el error capturado
        THROW;
    END CATCH;
END;

-- PRUEBAS

SELECT * FROM [Proyecto].[dbo].FACT_VENTAS
DELETE FROM [Proyecto].[dbo].FACT_VENTAS

SELECT * FROM  [Proyecto].[dbo].DIM_PROVEEDOR
DELETE  FROM  [Proyecto].[dbo].DIM_PROVEEDOR