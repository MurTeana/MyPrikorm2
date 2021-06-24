using MyPrikormWebAPI.Model.DB.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MyPrikormWebAPI.Services
{
    public interface IUserService
    {
        Task<User> Authenticate(string username, string password);
        Task<IEnumerable<User>> GetAll();
    }

    public class UserService : IUserService
    {
        private List<User> _users = new List<User>
        {
            new User {Id = 1, ChildName = "test", Email ="test", Password = "test", Pnoneno ="", Username = "Usertest"}
        };

        public async Task<User> Authenticate(string username, string password)
        {
            var user = await Task.Run(() => _users.SingleOrDefault(x => x.Username == username && x.Password == password));

            if (user == null)
                return null;

            return user;
            //return user.WithoutPassword();
        }

        public async Task<IEnumerable<User>> GetAll()
        {
            //           return await Task.Run(() => _users.WithoutPassword());
            return await Task.Run(() => _users);
        }



    }
}
