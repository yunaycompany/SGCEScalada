Ext.define('SGCEscalada.controller.DeportistaController', {
    extend: 'Ext.app.Controller',

    views: [
        'deportista.AdicionarDeportista',
        'deportista.EliminarDeportista',
        'deportista.ListaDeportista',
        'deportista.MenuDeportista',
        'deportista.GrdDeportistaCompetencia',
        'federacion.CbxFederaciones',
        'parroquia.CbxParroquias'
    ],

    refs: [
        {
            ref: 'adicionarDeportista',
            selector: 'adicionarDeportista'
        },
        {
            ref: 'eliminarDeportista',
            selector: 'eliminarDeportista'
        },
        {
            ref: 'listaDeportista',
            selector: 'listaDeportista'
        },
        {
            ref: 'menuDeportista',
            selector: 'menuDeportista'
        },
        {
            ref: 'grdDeportistaCompetencia',
            selector: 'grdDeportistaCompetencia'
        }
    ],

    stores: [
        'deportista.DeportistaStore'
    ],

    init: function () {
        this.control({
            'menuDeportista component[action=menuAdicionarDeportista]': {
                click: this.onClickMostrarAdicionarDeportista
            },
            'menuDeportista component[action=menuEliminarDeportista]': {
                click: this.onClickMostrarEliminarDeportista
            },
            'listaDeportista button[action=aceptarAdicionarDeportista]': {
                click: this.onClickMostrarAdicionarDeportista
            },
            'adicionarDeportista button[action=aceptarAdicionarDeportistaWnd]': {
                click: this.onClickAceptarAdicionarDeportista
            },
            'eliminarDeportista button[action=aceptarEliminarDeportista]': {
                click: this.onClickAceptarEliminarDeportista
            },
            'eliminarDeportista button[action=cancelarEliminarDeportistas]': {
                click: this.onClickCancelarAdicionarDeportista
            },
            'adicionarDeportista button[action=cancelarAdicionarDeportista]': {
                click: this.onClickCancelarAdicionarDeportista
            }
        });
    },

    onClickMostrarAdicionarDeportista: function(){
        Ext.widget('adicionarDeportista');
    },

    onClickMostrarEliminarDeportista: function(){
        Ext.widget('eliminarDeportista');
    },

    onClickAceptarAdicionarDeportista: function (){
        var me = this,
            form = me.getAdicionarDeportista().frmAdicionarDeportista.getForm();
        
        form.submit({
            url: 'insertarDeportista.htm',
            method: 'GET',
            success: function (form) {
                Ext.widget('msjInformacion',{mensaje: 'Deportista insertado satisfactoriamente.'});
                form.reset();
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error', action.result.msg);
                form.reset();
            }
        });
    },

    onClickAceptarEliminarDeportista: function (th){
        var me = this,
            grdDeportistaCompStr = me.getGrdDeportistaCompetencia().getStore(),
            arr = [],
            frm = th.up('form').getForm();

        grdDeportistaCompStr.each(function (r) {
            if (r.data.eliminar) {
                arr.push({
                    idDeportista: r.data.iddeportista
                });
            }
        });

        frm.submit({
            url: 'eliminarDeportistas.htm',
            method: 'GET',
            params: {
                deportistas: Ext.encode(arr)
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Deportistas eliminados satisfactoriamente.'});
                grdDeportistaCompStr.load();               
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    onClickCancelarAdicionarDeportista: function(th){
        th.up('window').destroy();
    }
});