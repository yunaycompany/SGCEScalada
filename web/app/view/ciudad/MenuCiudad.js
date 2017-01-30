Ext.define('SGCEscalada.view.ciudad.MenuCiudad',{
    extend: 'Ext.Button',
    alias:'widget.menuCiudad',
    text: 'Ciudad',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar ciudad',
                    action: 'menuAdicionarCiudad'
                },
                {
                    text: 'Eliminar ciudad',
                    action: 'menuEliminarCiudad'
                }
            ]
        });
        me.callParent(arguments);
    }
});