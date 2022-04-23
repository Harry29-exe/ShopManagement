<script lang="ts">
    import {page} from "$app/stores"
    import {onMount} from "svelte";
    import {popupStore} from "../../../stores/PopupStore";
    import {PurchaseInvoiceClient} from "../../../apiclient/PurchaseInvoiceClient";
    import {PurchaseInvoiceDTO} from "../../../dto/PurchaseInvoiceDTOs";

    let invoiceId: number = Number.parseInt($page.params.id);
    let invoice: PurchaseInvoiceDTO;
    onMount(async () => {
        let result = await PurchaseInvoiceClient.get(invoiceId);
        if (result.ok && result.result) {
            invoice = result.result;
        } else {
            popupStore.setNew(result.msg);
        }
    })
</script>

<div>

</div>