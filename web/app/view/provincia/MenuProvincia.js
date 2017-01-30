Ext.define('SGCEscalada.view.provincia.MenuProvincia',{
    extend: 'Ext.Button',
    alias:'widget.menuProvincia',
    text: 'Provincia',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar provincia',
                    action: 'menuAdicionarProvincia'
                },
                {
                    text: 'Eliminar provincia',
                    action: 'menuEliminarProvincia'
                }
            ]
        });
        me.callParent(arguments);
    }
});