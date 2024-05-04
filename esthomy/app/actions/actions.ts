"use server";

import { revalidatePath } from "next/cache";
import { redirect } from "next/navigation";

import { createClient } from "@/utils/supabase/server";

export async function findBroker(formData: FormData) {
  const supabase = createClient();
  const { data } = await supabase.auth.getUser();

  const _formData = { client_id: data.user?.id, aesthetic_id: 1 };
  const { error } = await supabase
    .from("find_partner_requests")
    .insert({ ..._formData });
  console.log("submitted", _formData, error);
}
export async function getUserByAuthId(id: string) {
  const supabase = createClient();

  const { data, error } = await supabase
    .from("profiles")
    .select()
    .eq("id", id)
    .single();
  //   console.log("data,error", data, error);
  return data;
}

export async function getPartnerRequests() {
  // add filters (transportation, etc..)
  // add isfinalize logic
  const supabase = createClient();

  const { data, error } = await supabase.from("find_partner_requests").select();

  console.log("data,error", data, error);
  return data;
}

export async function sendProposal(clientId: any, requestId: any) {
  // add filters (transportation, etc..)
  // add isfinalize logic
  const supabase = createClient();
  const { data } = await supabase.auth.getUser();
  const _data = {
    client_id: clientId,
    broker_id: data.user?.id,
    find_partner_request_id: requestId,
  };
  // const data = {id, }
  await supabase.from("proposals").insert({ ..._data });
}
