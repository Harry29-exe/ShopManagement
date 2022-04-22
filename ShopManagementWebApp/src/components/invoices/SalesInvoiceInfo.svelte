<script lang="ts">
    import {SalesInvoiceDTOs} from "../../dto/SalesInvoiceDTOs";

    export let invoice: SalesInvoiceDTOs;
</script>

<table class="md:w-[70%] w-full">
    <tbody>
    <tr>
        <th>Invoice id</th>
        <td>{invoice.id}</td>
    </tr>
    <tr>
        <th>Buyer entity</th>
        <td>{invoice.businessEntity.entityName}</td>
    </tr>
    <tr>
        <th>Seller fullname</th>
        <td>{invoice.sellerFullname}</td>
    </tr>
    <tr>
        <th>Issue date</th>
        <td>{invoice.issueDate}</td>
    </tr>
    <tr>
        <th>Is payed</th>
        <td>{invoice.isPayed}</td>
    </tr>
    </tbody>
</table>

<style>
    th, td {
        @apply text-left border-y-2 border-black-alpha-200;
    }
</style>