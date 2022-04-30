import {RequestResult} from "./config/RequestResult";
import {CompanyInfoDTO} from "../dto/CompanyInfo";

export class CompanyInfoClient {
    
    public static async getInfo(): Promise<RequestResult<CompanyInfoDTO>> {
        return RequestResult.ok(new CompanyInfoDTO("ComputerShop u marka"));
    }
    
}