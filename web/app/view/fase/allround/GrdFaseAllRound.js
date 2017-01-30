Ext.define('SGCEscalada.view.fase.allround.GrdFaseAllRound', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.grdFaseAllRound',
    store: Ext.create('SGCEscalada.store.fase.allround.FaseAllRoundStore'),
    general:true,
    columnLines: true,
    selType: 'cellmodel',
    sortableColumns: false,
    columns: [
        {text: 'Nombre', dataIndex: 'nombre', flex: 3},
        {text: 'Provincia', dataIndex: 'provincia', flex: 2},
        {text: 'Categor&iacute;a', dataIndex: 'categoria', flex: 3},
        {text: 'Ranking<br>Velocidad', dataIndex: 'rankingV', align: 'center', flex: 1.5},
        {text: 'Puntos<br>Velocidad', dataIndex: 'puntosV', align: 'center', flex: 1.5},
        {text: 'Ranking<br>Dificultad', dataIndex: 'rankingD', align: 'center', flex: 1.5},
        {text: 'Puntos<br>Dificultad', dataIndex: 'puntosD', align: 'center', flex: 1.5},
        {text: 'Ranking<br>Bloque', dataIndex: 'rankingB', align: 'center', flex: 1.5},
        {text: 'Puntos<br>Bloque', dataIndex: 'puntosB', align: 'center', flex: 1.5},
        {text: 'Total<br>de Puntos', dataIndex: 'totalPuntos', align: 'center', flex: 1.5},
        {text: 'Ranking<br>Allround', dataIndex: 'ranking', align: 'center', flex: 1.5},
        {text: 'Puntos', dataIndex: 'punto', align: 'center', flex: 1.5},
        {text: 'Medalla', dataIndex: 'medalla', align: 'center', flex: 2}
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