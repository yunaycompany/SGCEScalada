Ext.define('SGCEscalada.view.canton.CbxCantones',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxCantones',
    fieldLabel: 'Cant&oacute;n',
    store: 'canton.CantonesStore',
    displayField: 'canton',
    name:'idCanton',
    valueField: 'idCanton',
    emptyText:'Seleccione...',
    editable:false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});