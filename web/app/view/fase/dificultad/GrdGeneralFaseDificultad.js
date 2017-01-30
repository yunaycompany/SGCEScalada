Ext.define('SGCEscalada.view.fase.dificultad.GrdGeneralFaseDificultad', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.grdGeneralFaseDificultad',
    store: Ext.create('SGCEscalada.store.fase.dificultad.FaseDificultadStore'),
    columnLines: true,
    selType: 'cellmodel',
    general: true,
    columns: [
        {text: 'Orden', dataIndex: 'orden', flex:1, sortable: false},
        {text: 'Nombre', dataIndex: 'nombre', flex: 4, sortable: false},
        {text: 'Provincia', dataIndex: 'provincia', flex: 4, sortable: false},
        {text: 'Categor&iacute;a', dataIndex: 'categoria', flex: 2, sortable: false},
        {text: 'Ranking', dataIndex: 'ranking', flex: 1, sortable: false},
        {text: 'Puntos', dataIndex: 'punto', flex: 1, sortable: false},
        {text: 'Medalla', dataIndex: 'medalla', flex: 2, sortable: false}
    ],

    initComponent: function () {
        var me = this;

        me.plugins = [
            Ext.create('Ext.grid.plugin.CellEditing', {
                clicksToEdit: 1
            })
        ];



        me.callParent(arguments);
    }
});