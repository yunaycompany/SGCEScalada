Ext.define('SGCEscalada.view.competencia.CbxCompetencias',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxCompetencias',
    fieldLabel: 'Competencia',
    store: 'competencia.CompetenciasStore',
    displayField: 'nombre',
    name:'idCompetencia',
    valueField: 'idCompetencia',
    emptyText:'Seleccione...',
    editable:false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});