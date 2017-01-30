Ext.define('SGCEscalada.view.parroquia.MenuParroquia',{
    extend: 'Ext.Button',
    alias:'widget.menuParroquia',
    text: 'Parroquia',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar parroquia',
                    action: 'menuAdicionarParroquia'
                },
                {
                    text: 'Eliminar parroquia',
                    action: 'menuEliminarParroquia'
                }
            ]
        });
        me.callParent(arguments);
    }
});