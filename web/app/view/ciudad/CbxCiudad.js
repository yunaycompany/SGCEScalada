Ext.define('SGCEscalada.view.ciudad.CbxCiudad', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxCiudad',
    fieldLabel: 'Ciudad',
    store: 'ciudad.CiudadStore',
    displayField: 'ciudad',
    name: 'idCiudad',
    valueField: 'idCiudad',
    emptyText: 'Seleccione...',
    editable: false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});