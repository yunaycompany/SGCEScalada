Ext.define('SGCEscalada.view.fase.CbxCategorias',{
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.cbxCategorias',
    fieldLabel: 'Categoria',
    store: Ext.create('Ext.data.Store', {
        fields: ['idCategoria','categoria'],
        data: [
            {idCategoria: 1, categoria: 'Infantil B Hombres'},
            {idCategoria: 2, categoria: 'Infantil B Mujeres'},
            {idCategoria: 3, categoria: 'Infantil A Hombres'},
            {idCategoria: 4, categoria: 'Infantil A Mujeres'},
            {idCategoria: 5, categoria: 'Prejuvenil Hombres'},
            {idCategoria: 6, categoria: 'Prejuvenil Mujeres'},
            {idCategoria: 7, categoria: 'Juvenil B Hombres'},
            {idCategoria: 8, categoria: 'Juvenil B Mujeres'},
            {idCategoria: 9, categoria: 'Juvenil A Hombres'},
            {idCategoria: 10, categoria: 'Juvenil A Mujeres'},
            {idCategoria: 11, categoria: 'Junior Hombres'},
            {idCategoria: 12, categoria: 'Junior Mujeres'},
            {idCategoria: 13, categoria: 'Senior Hombres'},
            {idCategoria: 14, categoria: 'Senior Mujeres'}
        ]
    }),
    displayField: 'categoria',
    name:'idCategoria',
    valueField: 'idCategoria',
    queryMode: 'local',
    emptyText:'Seleccione...',
    editable:false,
    allowBlank: false,
    padding: 5,
    margin: 5,
    blankText: 'Este campo es requerido.'
});

