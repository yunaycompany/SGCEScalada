Ext.define('SGCEscalada.view.deportista.MenuDeportista', {
    extend: 'Ext.Button',
    alias: 'widget.menuDeportista',
    text: 'Deportista',
    initComponent: function () {
        var me = this;

        if (cliente) {
            me.menu = Ext.create('Ext.menu.Menu', {
                border: false,
                items: [
                    {
                        text: 'Adicionar deportista',
                        action: 'menuAdicionarDeportista'

                    }

                ]
            });

        } else {
            me.menu = Ext.create('Ext.menu.Menu', {
                border: false,
                items: [
                    {
                        text: 'Adicionar deportista',
                        action: 'menuAdicionarDeportista'

                    },
                    {
                        text: 'Eliminar deportista',
                        action: 'menuEliminarDeportista'

                    }
                ]
            });
        }
        me.callParent(arguments);
    }
});