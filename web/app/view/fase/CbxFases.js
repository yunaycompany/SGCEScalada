Ext.define('SGCEscalada.view.fase.CbxFases',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxFases',
    fieldLabel: 'Fase',
    store: Ext.create('Ext.data.Store', {
        fields: ['idFase','fase'],
        data: [
            {idFase: 1, fase: 'Fase dificultad'},
            {idFase: 2, fase: 'Fase dificultad general'},
            {idFase: 3, fase: 'Fase velocidad clasificatoria'},
            {idFase: 4, fase: 'Fase velocidad final'},
            {idFase: 5, fase: 'Fase velocidad general'},
            {idFase: 6, fase: 'Fase bloque clasificatoria'},
            {idFase: 7, fase: 'Fase bloque semifinal'},
            {idFase: 8, fase: 'Fase bloque final'},
            {idFase: 9, fase: 'Fase bloque general'},
            {idFase: 'all', fase: 'Fase ALLROUND'}/**/
        ]
    }),
    displayField: 'fase',
    name:'idFase',
    valueField: 'idFase',
    queryMode: 'local',
    emptyText:'Seleccione...',
    editable:false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});