Ext.define('SGCEscalada.view.fase.dificultad.CbxPresa',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxPresa',
    action: 'cbxPresaGrdFaseDificultad',
    store: Ext.create('Ext.data.Store', {
        fields: ['nombre', 'presa'],
        data: [
            {nombre: 'TOP', presa: 100}
        ]
    }),
    queryMode: 'local',
    displayField: 'nombre',
    valueField: 'presa'
});