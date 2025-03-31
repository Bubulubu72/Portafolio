Sub Clientes_Haga_clic_en()
    Load FormularioClientes
    FormularioClientes.Show
End Sub

' ///////////////////////////

Sub Proveedor_Haga_click()
    Load FormularioProveedores
    FormularioProveedores.Show 
End Sub

' ///////////////////////////

Sub Abrir_Cliente()
     Sheets("Adm. Clientes").Activate 
End Sub

' ///////////////////////////

Sub Abrir_Proveedores()
     Sheets("Adm. Proveedores").Activate
End Sub

' ///////////////////////////

Sub Abrir_Almacen()
    Sheets("Adm. Almacen").Activate
End Sub

' ///////////////////////////

Sub Abrir_Personal()
    Sheets("Adm. Personal").Activate
End Sub

' ///////////////////////////

Sub Abrir_Facturas()
    Sheets("Facturacion").Activate
End Sub

' ///////////////////////////

Sub Abrir_Reportes()
    Sheets("Reportes").Activate
End Sub

' ///////////////////////////

Sub Abrir_Dashboard()
    Sheets("Dashboard").Activate
End Sub

' ///////////////////////////

Sub MENU()
    Sheets("Menu Principal").Activate
End Sub

' ///////////////////////////

Sub AgregarAlmacen()
    Load FormularioAlmacen
    FormularioAlmacen.Show
End Sub

' ///////////////////////////

Sub AgregarPersonal()
    Load FormularioPersonal
    FormularioPersonal.Show
End Sub

' ///////////////////////////

Sub GenerarFactura()
    Dim ws As Worksheet
    Dim Cliente As Integer
    Dim Nombre As String
    Dim Ciudad As String
    Dim Factura As Integer
    Dim Fecha As String
    Dim Telefono As String
    Dim Cantidad As String
    Dim SubTotal As Double
    Dim Total As Double
    
    If IsEmpty(Range("B4").Value) Then
        MsgBox "No. Cliente está vacía"
        Exit Sub
    End If
    
    If IsEmpty(Range("G4").Value) Then
        MsgBox "No. Factura está vacía"
        Exit Sub
    End If
    
    ' Establecer la referencia a la hoja activa (o puedes usar una hoja específica)
    Set ws = ThisWorkbook.Sheets("Facturacion")

    ' Recopilar datos de celdas específicas
    Cliente = ws.Range("B4").Value
    Nombre = ws.Range("B5").Value
    Ciudad = ws.Range("B6").Value
    
    Factura = ws.Range("G4").Value
    Fecha = ws.Range("G5").Value '
    Telefono = ws.Range("G6").Value
    
    Cantidad = ws.Range("B16").Value
    SubTotal = ws.Range("G15").Value
    Total = ws.Range("G16").Value
    
    ' Obtener la siguiente fila vacía en la tabla
    With Sheets("Facturacion")
        fila = .ListObjects("TablaFacturas").ListRows.Count + 1
    End With
    
    ' Agregar los datos a la tabla
    With Sheets("Facturacion").ListObjects("TablaFacturas")
        .ListRows.Add ' Añadir una nueva fila a la tabla
        .ListRows(fila).Range(1, 1).Value = Cliente
        .ListRows(fila).Range(1, 2).Value = Nombre
        .ListRows(fila).Range(1, 3).Value = Ciudad
        .ListRows(fila).Range(1, 4).Value = Factura
        .ListRows(fila).Range(1, 5).Value = Fecha
        .ListRows(fila).Range(1, 6).Value = Telefono
        .ListRows(fila).Range(1, 7).Value = Cantidad
        .ListRows(fila).Range(1, 8).Value = SubTotal
        .ListRows(fila).Range(1, 9).Value = Total
    End With
    
    ' Confirmación de que los datos se agregaron
    MsgBox "Datos agregados correctamente", vbInformation
    
End Sub

' ///////////////////////////

Sub BorrarCeldasFactura()
    Dim celda As Range
    For Each celda In Sheets("Facturacion").Range("B4")
        celda = ""
    Next celda

    For Each celda In Sheets("Facturacion").Range("G4")
        celda = ""
    Next celda
    
    For Each celda In Sheets("Facturacion").Range("A10:B13")
        celda = ""
    Next celda 
End Sub

' ///////////////////////////

Sub Borrar_Reporte()
    Dim celda As Range
    For Each celda In Sheets("Reportes").Range("B4:C4")
        celda = ""
    Next celda
    
    For Each celda In Sheets("Reportes").Range("A8:D13")
        celda = ""
    Next celda

    For Each celda In Sheets("Reportes").Range("B15:C15")
        celda = ""
    Next celda
End Sub

' ///////////////////////////

Sub Generar_Reporte()
    Dim ws As Worksheet
    Dim tipoReporte As String
    Dim Fecha As String, motivo As String, realizadoPor As String

    ' Referencia a la hoja "Reportes"
    Set ws = ThisWorkbook.Sheets("Reportes") ' Cambia "Reportes" por el nombre de tu hoja

    ' Verificar qué botón está seleccionado
    If ws.OLEObjects("OptionButton1").Object.Value = True Then
        tipoReporte = "ALMACÉN"
    ElseIf ws.OLEObjects("OptionButton2").Object.Value = True Then
        tipoReporte = "PERSONAL"
    Else
        MsgBox "Por favor, selecciona un tipo de reporte.", vbExclamation, "Error"
        Exit Sub
    End If

    ' Obtener los valores de las celdas
    Fecha = Trim(ws.Range("B4").Value) ' Celda de Fecha
    realizadoPor = Trim(ws.Range("B15").Value) ' Celda de Realizado por

    ' Concatenar las celdas del rango de Motivo
    motivo = ""
    Dim celda As Range
    For Each celda In ws.Range("A8:D13")
        If Trim(celda.Value) <> "" Then
            motivo = motivo & celda.Value & vbCrLf
        End If
    Next celda

    ' Validar que los campos requeridos no estén vacíos
    If Fecha = "" Or motivo = "" Or realizadoPor = "" Then
        MsgBox "Por favor, completa todos los campos antes de generar el reporte.", vbExclamation, "Error"
        Exit Sub
    End If

    ' Mostrar los datos capturados
    'MsgBox "Reporte generado con los siguientes datos:" & vbCrLf & _
     '      "Fecha: " & fecha & vbCrLf & _
     '      "Motivo: " & motivo & vbCrLf & _
    '       "Realizado por: " & realizadoPor & vbCrLf & _
    '       "Tipo de Reporte: " & tipoReporte, vbInformation, "Datos del Reporte"

    ' Aquí puedes agregar la lógica para guardar los datos en otra hoja, archivo, etc.
    
    ' Obtener la siguiente fila vacía en la tabla
    With Sheets("Reportes")
        fila = .ListObjects("TablaReportes").ListRows.Count + 1
    End With
    
    ' Agregar los datos a la tabla
    With Sheets("Reportes").ListObjects("TablaReportes")
        .ListRows.Add ' Añadir una nueva fila a la tabla
        .ListRows(fila).Range(1, 1).Value = tipoReporte    ' Columna 1 (Tipo)
        .ListRows(fila).Range(1, 2).Value = Fecha          ' Columna 2 (fecha)
        .ListRows(fila).Range(1, 3).Value = motivo         ' Columna 3 (motivo)
        .ListRows(fila).Range(1, 4).Value = realizadoPor   ' Columna 4 (Realizado x)
    End With
    
    ' Confirmación de que los datos se agregaron
    MsgBox "Datos agregados correctamente", vbInformation
End Sub

' ///////////////////////////

Sub CopiarYRenombrarHoja()
    Dim wsOriginal As Worksheet
    Dim nuevaHoja As Worksheet
    Dim nuevoNombre As String
    Dim hojaEncontrada As Boolean
    Dim hojaNombre As String

    ' Nombre de la hoja a copiar
    hojaNombre = "Adm. Personal"

    ' Validar si la hoja existe
    hojaEncontrada = False
    On Error Resume Next
    Set wsOriginal = ThisWorkbook.Sheets(hojaNombre)
    On Error GoTo 0
    If wsOriginal Is Nothing Then
        MsgBox "La hoja '" & hojaNombre & "' no existe en el libro.", vbExclamation, "Error"
        Exit Sub
    End If

    ' Copiar la hoja
    wsOriginal.Copy After:=ThisWorkbook.Sheets(ThisWorkbook.Sheets.Count)

    ' Referencia a la nueva hoja copiada
    Set nuevaHoja = ThisWorkbook.Sheets(ThisWorkbook.Sheets.Count)

    ' Asignar un nuevo nombre
    nuevoNombre = "Creadores" ' Cambia esto por el nombre deseado
    On Error Resume Next
    nuevaHoja.Name = nuevoNombre
    If Err.Number <> 0 Then
        MsgBox "El nombre '" & nuevoNombre & "' ya existe. Cambia el nombre y vuelve a intentarlo.", vbExclamation, "Error al Renombrar"
        Err.Clear
    Else
        MsgBox "La hoja fue copiada y renombrada a: " & nuevoNombre, vbInformation, "Éxito"
    End If
    On Error GoTo 0
End Sub
