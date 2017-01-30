Ext.define('SGCEscalada.view.fase.bloque.GrdFaseBloqueFinal', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.grdFaseBloqueFinal',
    store: Ext.create('SGCEscalada.store.fase.bloque.FaseBloqueStore'),
    bloque: true,
    columnLines: true,
    selType: 'cellmodel',
    sortableColumns: false,
    columns: [
        {text: 'Nombre', dataIndex: 'nombre', flex: 5},
        {text: 'Provincia', dataIndex: 'provincia', flex: 5},
        {text: 'Categor&iacute;a', dataIndex: 'categoria', flex: 5},
        {text: 'T1', dataIndex: 't1', align: 'center', width: 55},
        {text: 'B1', dataIndex: 'b1', align: 'center', width: 55},
        {text: 'T2', dataIndex: 't2', align: 'center', width: 55},
        {text: 'B2', dataIndex: 'b2', align: 'center', width: 55},
        {text: 'T3', dataIndex: 't3', align: 'center', width: 55},
        {text: 'B3', dataIndex: 'b3', align: 'center', width: 55},
        {text: 'T4', dataIndex: 't4', align: 'center', width: 55},
        {text: 'B4', dataIndex: 'b4', align: 'center', width: 55},
        {text: 'Total<br>TOP', dataIndex: 'totalTop', align: 'center', flex: 2},
        {text: 'Intentos<br>a TOP', dataIndex: 'intentosTop', align: 'center', flex: 2},
        {text: 'Total<br>bonos', dataIndex: 'totalBonos', align: 'center', flex: 2},
        {text: 'Intentos<br>a bonos', dataIndex: 'intentosBonos', align: 'center', flex: 2},
        {text: 'Ranking', dataIndex: 'ranking', align: 'center', flex: 2}
    ],

    initComponent: function () {
        var me = this;

        if (!cliente) {
            me.columns[3].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};
            me.columns[4].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};
            me.columns[5].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};
            me.columns[6].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};
            me.columns[7].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};
            me.columns[8].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};
            me.columns[9].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};
            me.columns[10].editor = {xtype: 'numberfield', minValue: 0, maxValue: 25};

            /*me.columns[11].editor = {xtype: 'numberfield', minValue: 0};
            me.columns[12].editor = {xtype: 'numberfield', minValue: 0};
            me.columns[13].editor = {xtype: 'numberfield', minValue: 0};
            me.columns[14].editor = {xtype: 'numberfield', minValue: 0};*/
        }

        me.plugins = [
            Ext.create('Ext.grid.plugin.CellEditing', {
                clicksToEdit: 1
            })
        ];
        me.callParent(arguments);
    }
});