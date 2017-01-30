Ext.define('SGCEscalada.view.lugar.CbxLugares', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxLugares',
    fieldLabel: 'Lugar',
    labelAlign: 'top',
    store: 'lugar.LugaresStore',
    displayField: 'lugar',
    name: 'idLugar',
    valueField: 'idLugar',
    emptyText: 'Seleccione...',
    editable: false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});