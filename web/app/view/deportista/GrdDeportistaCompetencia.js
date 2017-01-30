Ext.define('SGCEscalada.view.deportista.GrdDeportistaCompetencia', {
    extend: 'Ext.grid.Panel',
    alias:'widget.grdDeportistaCompetencia',
    store: Ext.create('SGCEscalada.store.competencia.DeportistasCompetenciaStore'),
    height: 350,

    initComponent: function(){
        var me = this;

        me.columns = [
            {xtype: 'checkcolumn', text:'Adicionar', dataIndex:'adicionado',flex:1},
            {text: 'Nombre', dataIndex: 'nombre', flex:3, align: 'center'},
            {text: 'Provincia', dataIndex: 'provincia', flex: 2, align: 'center'}
        ];

        me.btnAdicionarDeportistaAdicionarDeportistaCompetencia = Ext.create('Ext.button.Button', {
            text: 'Adicionar deportista',
            action: 'adicionarDeportistaGrdDeportistaCompetencia'
        });

        if(!me.eliminar){
            me.bbar = ['->', me.btnAdicionarDeportistaAdicionarDeportistaCompetencia];
        }


        me.callParent(arguments);
    }
});