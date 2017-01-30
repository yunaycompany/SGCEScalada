Ext.define('SGCEscalada.view.fase.bloque.GrdFaseBloqueGeneral', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.grdFaseBloqueGeneral',
    store: Ext.create('SGCEscalada.store.fase.bloque.FaseBloqueStore',{
        general: true
    }),
    general:true,
    columnLines: true,
    selType: 'cellmodel',
    columns: [
        {text: 'Nombre', dataIndex: 'nombre', flex: 3},
        {text: 'Provincia', dataIndex: 'provincia', flex: 3},
        {text: 'Categor&iacute;a', dataIndex: 'categoria', flex: 3},
        {text: 'Ranking', dataIndex: 'ranking', flex: 1},
        {text: 'Puntos', dataIndex: 'punto', flex: 1},
        {text: 'Medalla', dataIndex: 'medalla', flex: 1}
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