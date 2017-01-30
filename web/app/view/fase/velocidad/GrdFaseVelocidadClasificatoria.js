Ext.define('SGCEscalada.view.fase.velocidad.GrdFaseVelocidadClasificatoria', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.grdFaseVelocidadClasificatoria',
    store: Ext.create('SGCEscalada.store.fase.velocidad.FaseVelocidadStore'),/**FALTA CAMBIARLE EL STORE*/
    columnLines: true,
    selType: 'cellmodel',
    sortableColumns: false,
    columns: [
        {text: 'Ruta 1', dataIndex: 'ruta1', align:'center', flex: 1},
        {text: 'Ruta 2', dataIndex: 'ruta2', align: 'center', flex: 1},
        {text: 'Nombre', dataIndex: 'nombre', flex: 4},
        {text: 'Provincia', dataIndex: 'provincia', flex: 3},
        {text: 'Categor&iacute;a', dataIndex: 'categoria', flex: 4},
        {text: 'Tiempo 1', dataIndex: 'tiempo1', align: 'center', flex: 1},
        {text: 'Tiempo 2', dataIndex: 'tiempo2', align: 'center', flex: 1},
        {text: 'Mejor<br> tiempo', dataIndex: 'mejorTiempo', align: 'center', flex: 1},
        {text: 'Ranking', dataIndex: 'ranking', align: 'center', flex: 1}
    ],

    initComponent: function () {
        var me = this;
        if (!cliente) {
            me.columns[0].editor = {xtype: 'numberfield'};
            me.columns[1].editor = {xtype: 'numberfield'};
            me.columns[5].editor = {xtype: 'numberfield', minValue: 0.01};
            me.columns[6].editor = {xtype: 'numberfield', minValue: 0.01};
        }
        me.plugins = [
            Ext.create('Ext.grid.plugin.CellEditing', {
                clicksToEdit: 1
            })
        ];
        me.callParent(arguments);
    }
});