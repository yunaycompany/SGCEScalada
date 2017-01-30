Ext.define('SGCEscalada.view.usuario.MenuUsuario',{
    extend: 'Ext.Button',
    alias:'widget.menuUsuario',
    text: 'Usuario',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar usuario',
                    action: 'menuAdicionarUsuario'
                }
            ]
        });
        me.callParent(arguments);
    }
});