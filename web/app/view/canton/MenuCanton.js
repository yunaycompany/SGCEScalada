Ext.define('SGCEscalada.view.canton.MenuCanton',{
    extend: 'Ext.Button',
    alias:'widget.menuCanton',
    text: 'Cant&oacute;n',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar cant&oacute;n',
                    action: 'menuAdicionarCanton'
                },
                {
                    text: 'Eliminar cant&oacute;n',
                    action: 'menuEliminarCanton'
                }
            ]
        });
        me.callParent(arguments);
    }
});