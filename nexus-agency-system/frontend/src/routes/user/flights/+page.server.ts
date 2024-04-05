export function load({ locals, cookies }) {
  return {
    user: locals.user,
    token: cookies.get("token")
  };
}
