Ext.define('SGCEscalada.view.fase.dificultad.GrdFaseDificultad', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.grdFaseDificultad',
    store: Ext.create('SGCEscalada.store.fase.dificultad.FaseDificultadStore'),
    columnLines: true,
    selType: 'cellmodel',
    sortableColumns: false,
    columns: [
        {text: 'Nombre', dataIndex: 'nombre', flex:3, sortable: false},
        {text: 'Provincia', dataIndex: 'provincia', flex: 3, sortable: false},
        {text: 'Categor&iacute;a', dataIndex: 'categoria', flex: 2, sortable: false},
        {text: 'Presa', dataIndex: 'presa', flex: 1, sortable: false},
        {text: 'Agarre', dataIndex: 'agarre', flex: 1, sortable: false},
        {text: 'Puntos', dataIndex: 'puntos', flex: 1},
        {text: 'Ranking', dataIndex: 'ranking', flex: 1, sortable: false}
    ],

    initComponent: function () {
        var me = this;

        if (!cliente) {
            me.columns[3].editor = {
                xtype: 'cbxPresa',
                action: 'editorCbxPresaFaseDificultad',
                invalidText: 'Solo admite n&uacute;meros y la palabra \'TOP\''
            };
            me.columns[4].editor = {xtype: 'cbxAgarre'};
        }

        me.plugins = [
            Ext.create('Ext.grid.plugin.CellEditing', {
                clicksToEdit: 1
            })
        ];



        me.callParent(arguments);
    }
});