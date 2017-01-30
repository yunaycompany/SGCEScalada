Ext.define('SGCEscalada.controller.UsuarioController', {
    extend: 'Ext.app.Controller',

    views: [
        'usuario.ListaUsuario',
        'usuario.AdicionarUsuario',
        'usuario.ModificarUsuario',
        'usuario.MenuUsuario'

    ],

    refs: [
        {
            ref: 'ListaUsuario',
            selector: 'ListaUsuario'
        },
        {
            ref: 'ModificarUsuario',
            selector: 'ModificarUsuario'
        },
        {
            ref: 'adicionarUsuario',
            selector: 'adicionarUsuario'
        },
        {
            ref: 'menuUsuario',
            selector: 'menuUsuario'
        }
    ],

    stores: [
        'usuario.UsuarioStore'
    ],

    init: function () {
        this.control({
            'adicionarUsuario button[action=Aceptar]': {
                click: this.onClickAdicionarUsuario
            },
            'ListaUsuario button[action=Adicionar]': {
                click: this.onClickMostrarAdicionarUsuario
            },
            'ListaUsuario button[action=Modificar]': {
                click: this.onClickMostrarModificarUsuario
            },
            'adicionarUsuario button[action=Cancelar]': {
                click: this.onClickCancelarAdicionarUsuario
            },
            'ListaUsuario button[action=Eliminar]': {
                click: this.onClickMostrarEliminarUsuario
            },
            'ModificarUsuario button[action=Modificar]': {
                click: this.onClickModificarUsuario
            },
            'menuUsuario component[action=menuAdicionarUsuario]': {
                click: this.onClickMenuAdicionarUsuario
            }
        });
    },

    onClickAdicionarUsuario: function (th) {
        var form = th.up('form').getForm();

        form.submit({
            url: 'insertarUsuario.htm',
            success: function (form) {
                Ext.widget('msjInformacion', {mensaje: 'Usuario insertado satisfactoriamente.'});
                form.reset();
              
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error.;', action.result.msg);
            }
        });
    },

    onClickMostrarAdicionarUsuario: function () {
        Ext.widget('adicionarUsuario').show();
    },

    onClickMostrarEliminarUsuario: function (th) {
        var gridUsuario = th.up('window').down('grid');
        var seleccionados = gridUsuario.getSelectionModel().selected.items;
        if (seleccionados[0]) {
            //Ext.Msg.alert('Est&aacute; seguro que quiere eliminar al usuario?', seleccionados[0].data['usuario']);
            Ext.Msg.show({
                title: 'Advertencia',
                msg: 'Est&aacute; seguro que quiere eliminar al usuario: ' + seleccionados[0].data['usuario'],
                buttons: Ext.Msg.YESNO,
                icon: Ext.Msg.QUESTION,
                fn: function (btn) {
                    console.log(btn);
                    if (btn == 'yes') {
                        Ext.Ajax.request({
                            url: 'eliminarUsuarios.htm',
                            params: {
                                idUsuario: seleccionados[0].data['idusuario']
                            },
                            success: function () {
                                Ext.widget('msjInformacion', {mensaje: 'Usuario eliminado satisfactoriamente.'});
                               
                            }
                        });
                        gridUsuario.getStore().reload();
                    }
                }
            });

        } else {
            Ext.Msg.alert('Advertencia.', "Debe seleccionar el usuario a eliminar.");

        }

    },

    onClickCancelarAdicionarUsuario: function () {
        this.getListaUsuario().down('grid').getStore().reload();
        this.getAdicionarUsuario().destroy();
    },

    onClickMostrarModificarUsuario: function (th) {
        var gridUsuario = th.up('window').down('grid');
        var seleccionados = gridUsuario.getSelectionModel().selected.items;

        if (seleccionados[0]) {
            var form = Ext.widget('ModificarUsuario',{title:'Mdificar usuario: ' + seleccionados[0].data['usuario'] + "."}).down('form');
            form.getForm().setValues(
                [
                    {id: 'txfUsuario', value: seleccionados[0].data['usuario']},
                    {id: 'idUsuario', value: seleccionados[0].data['idusuario']}
                ]
            );

        } else {
            Ext.Msg.alert('Advertencia.', "Debe seleccionar el usuario a modificar.");
        }
    },

    onClickModificarUsuario: function (th) {
        var form = th.up('form').getForm();
        var me = this;
        form.submit({
            url: 'modificarUsuario.htm',
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Usuario modificado satisfactoriamente.'});
                me.getListaUsuario().down('grid').getStore().reload();
                me.getModificarUsuario().destroy();
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error.;', action.result.msg);
            }
        });

    },

    onClickMenuAdicionarUsuario: function () {
        Ext.widget('adicionarUsuario');

    }
});