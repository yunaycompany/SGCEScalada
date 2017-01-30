Ext.define('SGCEscalada.view.fase.velocidad.GrdFaseVelocidadFinal', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.grdFaseVelocidadFinal',
    store: Ext.create('SGCEscalada.store.fase.velocidad.FaseVelocidadStore',{
        final:true
    }),
    columnLines: true,
    selType: 'cellmodel',
    sortableColumns: false,
    columns: [
        {text: 'Nombre', dataIndex: 'nombre', flex:2},
        {text: 'Provincia', dataIndex: 'provincia', flex: 2},
        {text: 'Categor&iacute;a', dataIndex: 'categoria', flex: 2},
        {text: 'Tiempo', dataIndex: 'tiempoFinal', flex: 1}
    ],

    initComponent: function () {
        var me = this;

        if(!cliente){
            me.columns[3].editor = {
                xtype:'numberfield',
                minValue:0.01
            }
        }

        me.plugins = [
            Ext.create('Ext.grid.plugin.CellEditing', {
                clicksToEdit: 1
            })
        ];
        me.callParent(arguments);
    }
});