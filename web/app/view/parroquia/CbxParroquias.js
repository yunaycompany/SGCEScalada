Ext.define('SGCEscalada.view.parroquia.CbxParroquias', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxParroquias',
    fieldLabel: 'Parroquia',
    store: 'parroquia.ParroquiasStore',
    displayField: 'parroquia',
    name: 'idParroquia',
    valueField: 'idParroquia',
    emptyText: 'Seleccione...',
    editable: false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});