Ext.define('SGCEscalada.view.federacion.CbxFederaciones',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxFederaciones',
    fieldLabel: 'Federaci&oacute;n',
    labelAlign:'top',
    store: 'federacion.FederacionesStore',
    displayField: 'federacion',
    name:'idFederacion',
    valueField: 'idFederacion',
    emptyText:'Seleccione...',
    editable:false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});

