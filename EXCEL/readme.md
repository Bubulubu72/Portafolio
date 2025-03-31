# 📦 GameSuppliers Inventory System

Este repositorio contiene las macros VBA utilizadas en el archivo de Excel para la gestión de inventario de la empresa **GameSuppliers** 
asi como el archivo en el formato .zip

## 📑 Descripción

El archivo de Excel maneja diferentes áreas de la empresa, incluyendo:
- **Clientes**
- **Proveedores**
- **Almacén**
- **Personal**
- **Facturas**
- **Reportes**
- **Gráficos (Dashboard)**

Las macros en este repositorio permiten una navegación eficiente entre estas secciones y la apertura de formularios específicos, además de realizar ciertas funciones automatizadas.

## 📜 Estructura del Código VBA

### 🔹 Formularios
- `Clientes_Haga_clic_en()`: Abre el formulario de clientes.
- `Proveedor_Haga_click()`: Abre el formulario de proveedores.
- `Almacen_Haga_clic_en()`: Abre el formulario de gestión de almacén.
- `Personal_Haga_clic_en()`: Abre el formulario de administración de personal.
- `Facturas_Haga_clic_en()`: Abre el formulario de facturación.
- `Reportes_Haga_clic_en()`: Abre el formulario de reportes.

### 🔹 Navegación entre Hojas
- `Abrir_Cliente()`: Activa la hoja de Administración de Clientes.
- `Abrir_Proveedores()`: Activa la hoja de Administración de Proveedores.
- `Abrir_Almacen()`: Activa la hoja de Administración de Almacén.
- `Abrir_Personal()`: Activa la hoja de Administración de Personal.
- `Abrir_Facturas()`: Activa la hoja de Facturación.
- `Abrir_Reportes()`: Activa la hoja de Reportes.
- `Abrir_Dashboard()`: Activa la hoja de Dashboard.
- `MENU()`: Activa la hoja de Menú Principal.

### 🔹 Funciones Adicionales
- `Generar_Reporte()`: Automatiza la generación de reportes basados en los datos del inventario.
- `Actualizar_Stock()`: Ajusta automáticamente los niveles de stock en la hoja de almacén después de una venta o ingreso de mercancía.
- `Validar_Factura()`: Revisa la información ingresada en la hoja de facturación para evitar errores.

## 🚀 Instalación

1. Descargar el archivo Excel con macros habilitadas (`.xlsm`).
2. Habilitar macros en Excel (**Opciones > Centro de confianza > Configuración de macros**).
3. Importar los archivos VBA en el Editor de VBA (`ALT + F11` > Importar módulo).

## 📌 Notas
- Asegúrate de habilitar macros para un correcto funcionamiento.
- La estructura del archivo Excel debe coincidir con las hojas mencionadas en las macros.
- Este proyecto fue desarrollado como parte de una clase de Habilidades Digitales para demostrar conocimientos en VBA y Excel.

¡Gracias por revisar este proyecto! 🚀




