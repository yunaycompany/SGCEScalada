Ext.define('SGCEscalada.view.lugar.MenuLugar',{
    extend: 'Ext.Button',
    alias:'widget.menuLugar',
    text: 'Lugar',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar lugar',
                    action: 'menuAdicionarLugar'
                },
                {
                    text: 'Eliminar lugar',
                    action: 'menuEliminarLugar'
                }
            ]
        });
        me.callParent(arguments);
    }
});