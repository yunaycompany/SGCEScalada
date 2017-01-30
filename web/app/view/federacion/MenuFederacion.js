Ext.define('SGCEscalada.view.federacion.MenuFederacion',{
    extend: 'Ext.Button',
    alias:'widget.menuFederacion',
    text: 'Federaci&oacute;n',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Adicionar federaci&oacute;n',
                    action: 'menuAdicionarFederacion'
                },
                {
                    text: 'Eliminar fedraci&oacute;n',
                    action: 'menuEliminarFederacion'
                }
            ]
        });
        me.callParent(arguments);
    }
});