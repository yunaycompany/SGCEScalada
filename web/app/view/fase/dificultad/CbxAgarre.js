Ext.define('SGCEscalada.view.fase.dificultad.CbxAgarre',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxAgarre',
    action: 'cbxAgarreGrdFaseDificultad',
    store: Ext.create('Ext.data.Store', {
        fields: ['agarre'],
        data: [
            {agarre: '+'}
        ]
    }),
    queryMode: 'local',
    displayField: 'agarre',
    valueField: 'agarre'
});