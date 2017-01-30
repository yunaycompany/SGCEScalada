Ext.define('SGCEscalada.view.competencia.MenuCompetencia',{
    extend: 'Ext.Button',
    alias:'widget.menuCompetencia',
    text: 'Competencia',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar competencia',
                    action: 'menuAdicionarCompetencia'
                },
                {
                    text: 'Adicionar deportista a competencia',
                    action: 'menuAdicionarDeportistaCompetencia'
                },
                {
                    text: 'Eliminar competencia',
                    action: 'menuEliminarCompetencia'
                }
            ]
        });
        me.callParent(arguments);
    }
});