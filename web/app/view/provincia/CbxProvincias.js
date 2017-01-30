Ext.define('SGCEscalada.view.provincia.CbxProvincias',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxProvincias',
    fieldLabel: 'Provincia',
    store: 'provincia.ProvinciasStore',
    displayField: 'provincia',
    name:'idProvincia',
    valueField: 'idProvincia',
    emptyText:'Seleccione...',
    editable:false,
    allowBlank: false,
    blankText: 'Este campo es requerido.',
    padding: 5,
    margin: 5
});